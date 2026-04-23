import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductService } from '../product.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-addproduct',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './addproduct.component.html',
  styleUrl: './addproduct.component.css'
})
export class AddproductComponent 
{
  data = {
    prodName: '',
    prodDesc: '',
    price: '',
    stock: '',
    prodImage: ''
  };

  msg = '';
  success = false;

  constructor(
    private productService: ProductService,
    private router: Router
  ) {}

  addProduct() {
    this.productService.addProduct(this.data).subscribe({
      next: () => {
        this.msg = 'Product added successfully';
        this.success = true;

        // redirect after add
        setTimeout(() => {
          this.router.navigate(['/products']);
        }, 1000);
      },
      error: (err) => {
        console.log(err);
        this.msg = 'Failed to add product';
        this.success = false;
      }
    });
  }

}
