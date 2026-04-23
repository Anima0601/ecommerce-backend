import { Component } from '@angular/core';
import { OrderService } from '../order.service';
import { UserService } from '../user.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-orders',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent 
{
   orders: any[] = [];
  custId!: number;

  constructor(
    private orderService: OrderService,
    private userService: UserService,
    private router: Router,
    private toastr: ToastrService
  ) {}

  ngOnInit() {
    this.custId = this.userService.getCustId()!;
    this.loadOrders();
  }

  loadOrders() {
    this.orderService.getOrders(this.custId).subscribe({
      next: (res) => this.orders = res,
      error: (err) => console.log(err)
    });
  }
  cancel(orderId: number) {
  this.orderService.cancelOrder(orderId).subscribe({
    next: () => {

      // ✅ SUCCESS TOAST
      this.toastr.error("Order cancelled");

      this.loadOrders();
    },
    error: (err) => {
      console.log(err);

      
      this.toastr.error(
        err.error?.message || "Failed to cancel order"
      );
    }
  });
}
  viewDetails(orderId: number) {
  this.router.navigate(['/orders', orderId]);
  }

}
