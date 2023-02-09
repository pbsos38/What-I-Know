using Microsoft.AspNetCore.Diagnostics;
using System.Net;
using System.Net.NetworkInformation;
using Web_Api.Middlewares;

namespace Web_Api.Extensions
{
    public static class ExceptionMiddlewareExtensions
    {

        /*public static void ConfigureExceptionHandler(this IApplicationBuilder app )
        {
            app.UseMiddleware<ExceptionMiddleware>();
        }*/
        public static void ConfigureBuiltInExceptionHandler(this IApplicationBuilder app1, WebApplication app)
        {

            if (app.Environment.IsDevelopment())
            {
                app.UseSwagger();
                app.UseSwaggerUI();
            }
            else
            {
                app.UseSwagger();
                app.UseSwaggerUI();
                app.UseExceptionHandler(
                    options =>
                    {
                        options.Run(
                            async context =>
                            {
                                context.Response.StatusCode = (int)HttpStatusCode.InternalServerError;
                                var ex = context.Features.Get<IExceptionHandlerFeature>();
                                if (ex != null)
                                {
                                    await context.Response.WriteAsync("From Exception Middleware Extension  File:"+ex.Error.Message);
                                }
                            });
                    });
            }
        }


    }
}
