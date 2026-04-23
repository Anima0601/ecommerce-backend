import { Component } from '@angular/core';
import { CartService } from '../cart.service';
import { UserService } from '../user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { OrderService } from '../order.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './cart.component.html'
})
export class CartComponent {

  cartItems: any[] = [];
  total = 0;
  custId!: number;

  constructor(
  private cartService: CartService,
  private userService: UserService,
  private orderService: OrderService,
  private router: Router,
  private toastr: ToastrService  
  ) {}

  ngOnInit() {
    this.custId = this.userService.getCustId()!; 

    this.loadCart();
  }

  loadCart() {
    this.cartService.getCart(this.custId).subscribe({
      next: (res) => {
        this.cartItems = res;
        this.calculateTotal();
      }
    });
  }

  calculateTotal() {
    this.total = this.cartItems.reduce(
      (sum, item) => sum + item.price * item.quantity,
      0
    );
  }

  increase(item: any) {
  if (item.quantity < item.stock) {
    this.cartService.updateQuantity(
      this.custId,
      item.prodId,
      item.quantity + 1
    ).subscribe(() => {
      this.loadCart();
      this.toastr.info("Quantity updated");
    });
  }
}

decrease(item: any) {
  if (item.quantity > 1) {
    this.cartService.updateQuantity(
      this.custId,
      item.prodId,
      item.quantity - 1
    ).subscribe(() => {
      this.loadCart();
      this.toastr.info("Quantity updated");
    });
  }
}

remove(prodId: number) {
  this.cartService.removeItem(this.custId, prodId)
    .subscribe(() => {
      this.loadCart();
      this.toastr.error("Item removed from cart");
    });
}

  placeOrder() {

  this.orderService.createOrder(this.custId).subscribe({
    next: (res) => {

      this.toastr.success("Order placed successfully");

      this.loadCart();
      this.router.navigate(['/orders']);
    },

    error: (err) => {
      console.log(err);

      this.toastr.error(
        err.error?.message || "Order failed"
      );
    }
  });
}
}