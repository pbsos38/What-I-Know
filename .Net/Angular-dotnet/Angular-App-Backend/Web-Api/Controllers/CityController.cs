using AutoMapper;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Security.Permissions;
using Web_Api.Data;
using Web_Api.Dtos;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CityController : Controller
    {

        private IUnitOfWork uow;
        private readonly IMapper mapper;

        public CityController(IUnitOfWork uow, IMapper mapper)
        {
            this.uow = uow;
            this.mapper = mapper;
        }

        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "Atlanta", "New York" };
        }
        [HttpGet("{id}")]
        public string Get(int id)
        {
            return ("Atlanta");
        }


        [HttpGet("cities")]
        public async Task<IActionResult> GetCities()
        {
            var cities = await uow.CityRepository.GetCitiesAsync();

            /*            var citiesDto = from c in cities
                                        select new CityDto()
                                        {
                                            Id = c.Id,
                                            Name = c.Name
                                        };*/

            var citiesDto = mapper.Map<IEnumerable<CityDto>>(cities);
            // Automapper will automatically show all the fields from the CTO class

            return Ok(citiesDto);
        }

        //Post api/city/AddCity?name=City4
        [HttpPost("AddCity")]

        /*//Post api/city/AddCity/CityName
        // Varible name should be same as of function parameters
        [HttpPost("AddCity/{name}")]

        public async Task<IActionResult> AddCity(string name)
        {
            City city = new City();
            city.Name = name;
            await dc.Cities.AddAsync(city);
            await dc.SaveChangesAsync();
            return Ok(city);
        }

*/

        //Post api/city/AddCity/cityObj
        // For adding a whole JSON object Varible name should be same as of function parameters
        [HttpPost("AddCityObj")]
        public async Task<IActionResult> AddCity(CityDto cityDto)
        {
            /*            City city = new City();
                        city.Name = name;

            */

            /* var city = new City
             {
                 Name = cityDto.Name,
                 LastUpdatedBy = 1,
                 LastUpdatedOn = DateTime.Now
             };*/

            var city = mapper.Map<City>(cityDto);
            city.LastUpdatedOn = DateTime.Now;
            city.LastUpdatedBy = 1;

            uow.CityRepository.AddCity(city);
            await uow.SaveAsync();
            return StatusCode(201);
        }

        [HttpDelete("delete/{id}")]
        public async Task<IActionResult> RemoveCity(int id)
        {
            //var city = await dc.Cities.FindAsync(id);
            //dc.Cities.Remove(city);
            uow.CityRepository.DeleteCity(id);
            await uow.SaveAsync();
            return Ok(id);
        }

    }
}
