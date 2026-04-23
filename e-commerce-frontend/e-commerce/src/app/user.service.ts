import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private custId: number | null = null;
  private username: string | null = null;
  private isLoggedInFlag = false;
  setUser(data: any) {
    this.custId = data.custId;
    this.username = data.username;
    this.isLoggedInFlag = true;
  }

  getCustId(): number | null {
    return this.custId;
  }

  getUsername(): string | null {
    return this.username;
  }

  clearUser() {
    this.custId = null;
    this.username = null;
    this.isLoggedInFlag = false;
  }
  isLoggedIn() {
    return this.isLoggedInFlag;
  }
}