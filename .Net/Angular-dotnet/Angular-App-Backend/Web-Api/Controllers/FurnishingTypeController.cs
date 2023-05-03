using AutoMapper;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Web_Api.Dtos;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Controllers
{
    public class FurnishingTypeController :BaseController
    {
        private readonly IUnitOfWork uow;
        private readonly IMapper mapper;

        public FurnishingTypeController(IUnitOfWork uow, IMapper mapper) {
            this.uow = uow;
            this.mapper = mapper;
        }

        [HttpGet("list")]
        [AllowAnonymous]
        public async Task<IActionResult> GetFurnishingTypeList()
        {
            var furnishingTypes = await uow.furnishingTypeRepository.GetFurnishingTypeAsync();
            var furnishingTypesDTO = mapper.Map<IEnumerable<FurnishingType>>(furnishingTypes);
            return Ok(furnishingTypesDTO);
        }
    }
}
