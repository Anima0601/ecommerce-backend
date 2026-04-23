import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../order.service';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-order-details',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent 
{
  products: any[] = [];
  orderId!: number;

  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService
  ) {}

  ngOnInit() {
    this.orderId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadProducts();
  }

  loadProducts() {
    this.orderService.getOrderProducts(this.orderId).subscribe({
      next: (res) => this.products = res,
      error: (err) => console.log(err)
    });
  }
}


