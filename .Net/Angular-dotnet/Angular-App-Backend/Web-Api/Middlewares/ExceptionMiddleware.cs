using System.Net;
using Web_Api.Errors;

namespace Web_Api.Middlewares
{
    public class ExceptionMiddleware
    {
        private readonly RequestDelegate next;
        private readonly ILogger<ExceptionMiddleware> logger;
        private readonly IHostEnvironment env;

        public ExceptionMiddleware(RequestDelegate next, ILogger<ExceptionMiddleware> logger,
            IHostEnvironment env)
        {
            this.next = next;
            this.logger = logger;
            this.env = env;
        }

        public async Task Invoke(HttpContext context)
        {
            try
            {
                await next(context);
            }
            catch (Exception ex)
            {

                ApiError response;
                HttpStatusCode statusCode = HttpStatusCode.InternalServerError;
                String message;
                var exceptionTye = ex.GetType();

                if (exceptionTye == typeof(UnauthorizedAccessException))
                {
                    statusCode = HttpStatusCode.Forbidden;
                    message = "You are not authorized!";
                }
                else
                {
                    statusCode = HttpStatusCode.InternalServerError;
                    message = "Some Unknow error Occured!";
                }

                if (env.IsDevelopment())
                {
                    response = new ApiError((int)statusCode,(message + ex.Message),ex.StackTrace.ToString());
                }
                else
                {
                    response = new ApiError((int)statusCode, (message + ex.Message));
                }

                logger.LogError(ex, ex.Message);
                context.Response.StatusCode = (int) statusCode;
                context.Response.ContentType = "application/json";
                await context.Response.WriteAsync("From Exception Middleware: "+response.ToString());
            }
        }
    }
}
