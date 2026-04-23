import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseUrl = 'http://localhost:8080/order';

  constructor(private http: HttpClient) {}

  createOrder(custId: number) {
    return this.http.post(
      `${this.baseUrl}/create/${custId}`,
      null,
      { withCredentials: true, responseType: 'text' }
    );
  }

  getOrders(custId: number) {
    return this.http.get<any[]>(
      `${this.baseUrl}/customer/${custId}`,
      { withCredentials: true }
    );
  }

  
  getOrderProducts(orderId: number) {
    return this.http.get<any[]>(
      `${this.baseUrl}/${orderId}/products`,
      { withCredentials: true }
    );
  }
  cancelOrder(orderId: number) {
    return this.http.put(
      `${this.baseUrl}/cancel/${orderId}`,
      null,
      { withCredentials: true, responseType: 'text' }
    );
  }
}
