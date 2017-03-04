import {Injectable} from "@angular/core";
import {User} from "../model/user.model";
import {Observable} from "rxjs";
import {Http, Response} from "@angular/http";
import "rxjs/Rx";
import {AuthHttp} from "angular2-jwt";

const url = '/api/users';

@Injectable()
export class UserService {
  constructor(private http: Http,
              private authHttp: AuthHttp) {
  }

  create(user: User): Observable<Response> {
    return this.http.post(url, user);
  }

  update(user: User): Observable<Response> {
    return this.authHttp.put(url, user).map(resp => resp.json());
  }

  get(id: number): Observable<User> {
    return this.authHttp.get(`${url}/${id}`)
      .map(resp => resp.json())
      .catch((error: any) => {
        return Observable.throw(error);
      });
  }

  getAll(): Observable<User[]> {
    return this.authHttp.get(`${url}`).map(resp => resp.json());
  }
}
