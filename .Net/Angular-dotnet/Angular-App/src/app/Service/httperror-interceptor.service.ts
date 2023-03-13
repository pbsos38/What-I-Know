import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, concatMap, Observable, of, retry, retryWhen, throwError } from "rxjs";
import { ErrorCode } from "../enums/enums";
import { AlertyfyService } from "./alertyfy.service";

@Injectable({
  providedIn: 'root'
})

export class HttpErrorInterceptorService implements HttpInterceptor {

  constructor(private alertify: AlertyfyService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Http Request Started!");
    return next.handle(request)
      .pipe(
        retryWhen(error => this.retryRequest(error, 10)),
        catchError((error: HttpErrorResponse) => {
          const errorMessage = this.setError(error);
          console.log(errorMessage);
          this.alertify.error(errorMessage);
          return throwError(errorMessage);
        })
      );
  }

  retryRequest(error: Observable<HttpErrorResponse>, retryCount: number): Observable<unknown> {

    return error.pipe(
      concatMap((checkErr: HttpErrorResponse,count: number) => {
        if(count<=retryCount){
          switch(checkErr.status){
            case ErrorCode.serverDown :
              return of(checkErr);
            case ErrorCode.unauthorized:
              return of(checkErr);
          }
        }
        return throwError(checkErr);
      })
    )
  }

  setError(error: HttpErrorResponse): string {
    let errorMessage = 'Unknown error occured';
    if (error.error instanceof ErrorEvent) {
      //Client side error
      errorMessage = error.error.message;
    } else {
      //server side error
      if (error.status !== 0) {
        errorMessage = error.error.errorMessage;
      }
    }

    return errorMessage;
  }

}