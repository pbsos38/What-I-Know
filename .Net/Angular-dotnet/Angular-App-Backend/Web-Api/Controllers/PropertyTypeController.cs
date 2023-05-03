using AutoMapper;
using Microsoft.AspNetCore.Mvc;
using Web_Api.Dtos;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Controllers
{
    public class PropertyTypeController :BaseController
    {
        private readonly IUnitOfWork uow;
        private readonly IMapper mapper;

        public PropertyTypeController(IUnitOfWork uow, IMapper mapper) {
            this.uow = uow;
            this.mapper = mapper;
        }

        [HttpGet("list")]
        public async Task<IActionResult> GetPropertyTypeList()
        {
            var propertyTypes = await uow.propertyTypeRepository.GetPropertyTypeAsync();
            var propertyTypesDTO = mapper.Map<IEnumerable<KeyValuePairDTO>>(propertyTypes);
            return Ok(propertyTypesDTO);
        }
    }
}
