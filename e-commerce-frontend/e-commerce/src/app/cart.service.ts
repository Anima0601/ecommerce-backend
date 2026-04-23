import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private baseUrl = 'http://localhost:8080/cart';

  constructor(private http: HttpClient) {}

  addToCart(custId: number, prodId: number, qty: number) {
    let params = new HttpParams()
      .set('custId', custId)
      .set('prodId', prodId)
      .set('qty', qty);

    return this.http.post(
      `${this.baseUrl}/add`,
      null,
      { params, withCredentials: true,  responseType: 'text' } 
    );
  }

  getCart(custId: number) {
    return this.http.get<any[]>(
      `${this.baseUrl}/${custId}`,
      { withCredentials: true } 
    );
  }

  removeItem(custId: number, prodId: number) {
    let params = new HttpParams()
      .set('custId', custId)
      .set('prodId', prodId);

    return this.http.delete(`${this.baseUrl}/remove`, { params, withCredentials: true,responseType: 'text'});
  }
  updateQuantity(custId: number, prodId: number, qty: number) {
  let params = new HttpParams()
    .set('custId', custId)
    .set('prodId', prodId)
    .set('qty', qty);

  return this.http.put(`${this.baseUrl}/update`, null, { params, withCredentials: true, responseType: 'text' });
  }

}