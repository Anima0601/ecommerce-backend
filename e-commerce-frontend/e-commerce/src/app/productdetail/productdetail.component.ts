import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from '../product.service';
import { CartService } from '../cart.service';
import { CommonModule } from '@angular/common';
import { UserService } from '../user.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-productdetail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './productdetail.component.html',
  styleUrl: './productdetail.component.css'
})
export class ProductdetailComponent {

  product: any;
  quantity: number = 1;
  custId!: number;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private cartService: CartService,
    private router: Router,
    private userService: UserService,
    private toastr: ToastrService 
  ) {}

  ngOnInit() {
    this.custId = this.userService.getCustId()!;
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.productService.getProductById(id).subscribe({
        next: (res) => this.product = res,
        error: () => this.toastr.error("Failed to load product")
      });
    }
  }

  increase() {
    if (this.quantity < this.product.stock) {
      this.quantity++;
    }
  }

  decrease() {
    if (this.quantity > 1) {
      this.quantity--;
    }
  }

  addToCart() {
    this.cartService.addToCart(
      this.custId,
      this.product.prodId,
      this.quantity
    ).subscribe({
      next: () => {
        this.toastr.success("Added to cart!", "Success");
        this.router.navigate(['/cart']);
      },
      error: (err) => {
        this.toastr.error(
          err.error?.message || "Failed to add to cart",
          "Error"
        );
      }
    });
  }
}