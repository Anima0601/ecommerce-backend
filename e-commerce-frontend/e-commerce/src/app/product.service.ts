import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/product';

  constructor(private http: HttpClient) {}

  getAllProducts(): Observable<any> {
    return this.http.get(this.baseUrl, {
      withCredentials: true
    });
  }

  getProductById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`, {
      withCredentials: true
    });
  }

  addProduct(data: any): Observable<any> {
    return this.http.post(this.baseUrl, data, {
      withCredentials: true
    });
  }
}