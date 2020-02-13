import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from './token-storage.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }

  getCategory(postId): Observable<any> {
    httpOptions.headers.set('Authorization', 'Bearer ' + this.tokenStorageService.getToken());

    const data = JSON.stringify({ id: postId });
    console.log('data stringify:', data);

    const dataAsJSON = JSON.parse(data);
    console.log('data JSON:', dataAsJSON);

    return this.http.get<any>('http://localhost:8090/category/get_category', { params: { id: postId } });

  }

  getCategories(): Observable<any> {
    httpOptions.headers.set('Authorization', 'Bearer ' + this.tokenStorageService.getToken());
    return this.http.get('http://localhost:8090/category/all_categories', httpOptions);
  }
}
