import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ProductService } from '../product.service';
import { CartService } from '../cart.service';

@Component({
  selector: 'app-products',
  standalone: true,
  imports: [RouterLink,CommonModule],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent 
{
  products: any[] = [];

constructor(private productService: ProductService) {}

  ngOnInit() {
  this.productService.getAllProducts().subscribe({
    next: (res) => {
      this.products = res;
    },
    error: (err) => {
      console.log(err);
    }
  });
  
}

}
