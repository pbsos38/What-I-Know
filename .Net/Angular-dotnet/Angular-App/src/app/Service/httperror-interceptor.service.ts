import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { catchError, Observable, throwError } from "rxjs";
import { AlertyfyService } from "./alertyfy.service";

@Injectable({
  providedIn:'root'
})

export class HttpErrorInterceptorService implements HttpInterceptor{

  constructor(private alertify:AlertyfyService){}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    console.log("Http Request Started!");
    return next.handle(request)
    .pipe(
      catchError((error: HttpErrorResponse) =>{
        const errorMessage = this.setError(error);
        console.log(errorMessage);
        this.alertify.error(errorMessage);
        return throwError(errorMessage);
      })
    );
  }

setError(error:HttpErrorResponse):string{
  let errorMessage = 'Unknown error occured';
  if(error.error instanceof ErrorEvent){
    //Client side error
    errorMessage = error.error.message;
  }else{
    //server side error
    if(error.status!==0){
      errorMessage = error.error.errorMessage;
    }
  }

  return errorMessage;
}

}