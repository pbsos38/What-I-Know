using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using Microsoft.IdentityModel.Tokens;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using Web_Api.Dtos;
using Web_Api.Errors;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Controllers
{
    public class AccountController : BaseController
    {
        private readonly IUnitOfWork uow;
        private readonly IMapper mapper;
        private readonly IConfiguration configuration;

        public AccountController(IUnitOfWork uow, IMapper mapper,IConfiguration configuration)
        {
            this.uow = uow;
            this.mapper = mapper;
            this.configuration = configuration;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginRegDto loginReq)
        {
            var user = await uow.UserRepository.Authenticate(loginReq.UserName, loginReq.Password);
            //var userDto =  mapper.Map<UserDto>(user);
            ApiError apiError = new ApiError();
            if(user == null)
            {
                apiError.ErrorCode = Unauthorized().StatusCode;
                apiError.ErrorMessage = "Invalid Username and password";
                apiError.ErrorDetails = "This error appers when provided user id or password does not exists";
                return Unauthorized(apiError);
            }
            else
            {
                var userDto = new UserDto();
                userDto.UserName = user.Username;
                userDto.token = createJWT(user);

                return Ok(userDto);
            }
        }


        [HttpPost("register")]
        public async Task<IActionResult> Register(LoginRegDto loginRegDto)
        {
            ApiError apiError = new ApiError();
            if(await uow.UserRepository.UserAlreadyExists(loginRegDto.UserName))
            {
                apiError.ErrorCode = BadRequest().StatusCode;
                apiError.ErrorMessage = "User Already existes, please try something else";
                return BadRequest(apiError);
            }

            uow.UserRepository.Register(loginRegDto.UserName, loginRegDto.Password);
            await uow.SaveAsync();
            return StatusCode(201);
        }


        private string createJWT(User user)
        {
/*            var config = new ConfigurationBuilder()
                            .SetBasePath(Directory.GetCurrentDirectory())
                            .AddJsonFile("appsettings.json").Build();*/
            var config2 = configuration.GetSection("AppSetting:Key").Value;
           // Console.WriteLine("asda"+ config["AppSetting:Key"]);
            
            var key =  new SymmetricSecurityKey(Encoding.UTF8
                .GetBytes(config2));

            var claims = new Claim[]
            {
                new Claim(ClaimTypes.Name,user.Username),
                new Claim(ClaimTypes.NameIdentifier, user.Id.ToString())
            };

            var signingCredentials = new SigningCredentials(
                key, SecurityAlgorithms.HmacSha256Signature);

            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = new ClaimsIdentity(claims),
                Expires = DateTime.UtcNow.AddDays(1),
                SigningCredentials = signingCredentials
            };

            var tokenHandler = new JwtSecurityTokenHandler();

            var token = tokenHandler.CreateToken(tokenDescriptor);

            return tokenHandler.WriteToken(token);
        }
    }
}
