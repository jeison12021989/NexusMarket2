# NexusMarket - end-to-end demo of the business flow against the running API.
# The API must be running on http://localhost:8080 (.\mvnw.cmd spring-boot:run).
# Usage:
#   powershell -ExecutionPolicy Bypass -File .\demo.ps1

$ErrorActionPreference = "Stop"
$base = "http://localhost:8080/api"
$stamp = Get-Date -Format "HHmmss"

function Send([string]$method, [string]$path, $body = $null) {
    $params = @{ Method = $method; Uri = "$base$path"; ContentType = "application/json" }
    if ($null -ne $body) { $params.Body = ($body | ConvertTo-Json) }
    Invoke-RestMethod @params
}

function Step([string]$title) {
    Write-Host ""
    Write-Host "== $title ==" -ForegroundColor Cyan
}

Step "1. Administrator onboards a seller"
$seller = Send "Post" "/sellers" @{ name = "Pedro Seller"; email = "pedro$stamp@mail.com"; document = "S$stamp" }
Write-Host "Seller id: $($seller.identifier) - status: $($seller.status)"

Step "2. A buyer registers"
$buyer = Send "Post" "/buyers" @{ name = "Juan Buyer"; email = "juan$stamp@mail.com"; document = "B$stamp" }
Write-Host "Buyer id: $($buyer.identifier) - status: $($buyer.status)"

Step "3. Seller publishes a physical product with a variant"
$product = Send "Post" "/products/physical" @{ sellerId = $seller.identifier; name = "Computer"; price = 450; weight = 2.5 }
Send "Post" "/products/$($product.identifier)/variants" @{ name = "Color"; value = "Black"; sku = "COM-$stamp" } | Out-Null
Write-Host "Product id: $($product.identifier) - price: $($product.price) - status: $($product.status)"

Step "4. Seller creates a warehouse and registers inventory"
$warehouse = Send "Post" "/warehouses" @{ sellerId = $seller.identifier; name = "Main Warehouse"; location = "Medellin" }
Send "Post" "/warehouses/$($warehouse.identifier)/inventory" @{ productId = $product.identifier; quantity = 10 } | Out-Null
Write-Host "Warehouse id: $($warehouse.identifier) - 10 units registered"

Step "5. Buyer adds 2 units to the cart"
Send "Post" "/buyers/$($buyer.identifier)/cart/items" @{ productId = $product.identifier; quantity = 2 } | Out-Null
$total = Send "Get" "/buyers/$($buyer.identifier)/cart/total"
Write-Host "Cart total: $total"

Step "6. Checkout creates the order"
$order = Send "Post" "/orders/checkout" @{ buyerId = $buyer.identifier }
Write-Host "Order id: $($order.identifier) - status: $($order.status)"

Step "7. Stock is reserved in the warehouse"
$reserved = Send "Post" "/warehouses/$($warehouse.identifier)/inventory/reserve" @{ productId = $product.identifier; quantity = 2 }
Write-Host "Reserved: $reserved"

Step "8. Order life cycle: pay, dispatch, deliver, complete"
$order = Send "Post" "/orders/$($order.identifier)/pay"
Write-Host "After pay: $($order.status)"
$shipment = Send "Post" "/orders/$($order.identifier)/dispatch" @{ carrier = "National Carrier" }
Write-Host "Shipment: $($shipment.status) via $($shipment.carrier)"
$order = Send "Post" "/orders/$($order.identifier)/deliver"
Write-Host "After deliver: $($order.status)"
$order = Send "Post" "/orders/$($order.identifier)/complete"
Write-Host "After complete: $($order.status)"

Step "9. Invoice"
$invoice = Send "Post" "/orders/$($order.identifier)/invoice" @{ invoiceNumber = "INV-$stamp" }
Write-Host "Invoice $($invoice.number): total $($invoice.total) - $($invoice.status)"

Step "10. Return and refund"
$return = Send "Post" "/returns" @{ orderId = $order.identifier; reason = "Changed my mind" }
$return = Send "Post" "/returns/$($return.id)/approve"
Write-Host "Return status: $($return.status)"
$refund = Send "Post" "/returns/$($return.id)/refund"
Write-Host "Refund: $($refund.amount) - $($refund.status)"

Step "11. Administrative report"
$report = Send "Post" "/reports" @{ name = "General Sales Report" }
Write-Host "Report generated: $report"

Step "12. Business rule violation (paying a completed order again)"
try {
    Send "Post" "/orders/$($order.identifier)/pay" | Out-Null
    Write-Host "Unexpected: no error was returned" -ForegroundColor Red
} catch {
    Write-Host "Expected 400 -> $($_.ErrorDetails.Message)" -ForegroundColor Yellow
}

Write-Host ""
Write-Host "Demo finished. Check the data in MySQL Workbench (nexusmarket_db)." -ForegroundColor Green
