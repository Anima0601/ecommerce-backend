import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProductsComponent } from './products/products.component';
import { CartComponent } from './cart/cart.component';
import { OrdersComponent } from './orders/orders.component';
import { authGuard } from './auth.guard';
import { ProductdetailComponent } from './productdetail/productdetail.component';
import { AddproductComponent } from './addproduct/addproduct.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { HomeComponent } from './home/home.component';


export const routes: Routes = [
    {path: '',component: HomeComponent},
    {path:"register",component:RegisterComponent},
    {path:"login",component:LoginComponent},
    {path:"products",component:ProductsComponent,canActivate: [authGuard] },
    {path: 'products/:id', component: ProductdetailComponent,canActivate: [authGuard]},
    {path: 'add-product',component: AddproductComponent,canActivate: [authGuard]},
    {path:"cart",component:CartComponent,canActivate: [authGuard]},
    {path:"orders",component:OrdersComponent,canActivate: [authGuard]},
    {path: 'orders/:id',component: OrderDetailsComponent,canActivate: [authGuard]}
];
