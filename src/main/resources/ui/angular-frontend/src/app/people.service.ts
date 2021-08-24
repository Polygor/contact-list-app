import {Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {People} from './people'
import {ActivatedRoute, Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class PeopleService {

private baseUrl="http://localhost:8080/api/people/all";

  constructor(
    private http: HttpClient, 
    ) { }

  getPeople(): Observable<People[]>{
    this.baseUrl = window.location.href.replace("4200", "8080");
    return this.http.get<People[]>(`${this.baseUrl}`)
  }
}
