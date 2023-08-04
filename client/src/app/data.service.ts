import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private apiUrl = 'https://example-api.com/data'; // Replace this with your API URL

  constructor(private http: HttpClient) {}

  sendData(jsonData: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, jsonData);
  }
}
