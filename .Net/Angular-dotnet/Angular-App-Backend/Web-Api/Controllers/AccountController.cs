using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using Web_Api.Dtos;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Controllers
{
    public class AccountController : BaseController
    {
        private readonly IUnitOfWork uow;
        private readonly IMapper mapper;

        public AccountController(IUnitOfWork uow, IMapper mapper)
        {
            this.uow = uow;
            this.mapper = mapper;
        }

        [HttpPost("login")]
        public async Task<IActionResult> Login(LoginRegDto loginReq)
        {
            var user = await uow.UserRepository.Authenticate(loginReq.UserName, loginReq.Password);
            var userDto =  mapper.Map<UserDto>(user);
            
            if(user == null)
            {
                return Unauthorized();
            }
            else
            {
                return Ok(userDto);
            }
        }

    }
}
