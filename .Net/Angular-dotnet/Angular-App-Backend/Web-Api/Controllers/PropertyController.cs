using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Web_Api.Dtos;
using Web_Api.Interfaces;

namespace Web_Api.Controllers
{
    public class PropertyController : BaseController
    {
        private readonly IUnitOfWork uow;
        private readonly IMapper mapper;

        public PropertyController(IUnitOfWork uow, IMapper mapper)
        {
            this.uow = uow;
            this.mapper = mapper;
        }

        //property/type/1
        [HttpGet("list/{sellRent}")]
        [AllowAnonymous]
        public async Task<IActionResult> GetPropertyList(int sellRent)
        {
            var properties =  await uow.PropertyRepository.GetPropertiesAsync(sellRent);
            var propertiesDTO = mapper.Map<IEnumerable<PropertyList>>(properties);
            return Ok(propertiesDTO);
        }

        //property/type/1
        [HttpGet("list/details/{id}")]
        [AllowAnonymous]
        public async Task<IActionResult> GetPropertyById(int id)
        {
            var properties = await uow.PropertyRepository.GetPropertyDetailsAsync(id);
            var propertiesDTO = mapper.Map<PropertyDetails>(properties);
            return Ok(propertiesDTO);
        }



    }
}
