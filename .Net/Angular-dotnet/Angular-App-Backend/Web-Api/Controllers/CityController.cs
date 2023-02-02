using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Web_Api.Data;
using Web_Api.Data.Repo;
using Web_Api.models;

namespace Web_Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class CityController : Controller
    {

        private ICityRepository repo;

        public CityController(ICityRepository repo)
        {
            this.repo = repo;
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
            var cities = await repo.GetCitiesAsync();
            return Ok(cities);
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

        public async Task<IActionResult> AddCity(City cityObj)
        {
/*            City city = new City();
            city.Name = name;
*/            repo.AddCity(cityObj);
            await repo.SaveAsync();
            return StatusCode(201);
        }

        [HttpDelete("delete/{id}")]
        public async Task<IActionResult> RemoveCity(int id)
        {
            //var city = await dc.Cities.FindAsync(id);
            //dc.Cities.Remove(city);
            repo.DeleteCity(id);
            await repo.SaveAsync();
            return Ok(id);
        }

    }
}
