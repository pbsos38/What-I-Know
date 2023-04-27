using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Data.Repo
{
    public class CityRepository : ICityRepository
    {
        private readonly DataContext dc;

        public CityRepository(DataContext dc)
        {
            this.dc = dc;
        }
        public void AddCity(City city)
        {
            dc.CityList.AddAsync(city);
        }

        public void DeleteCity(int CityId)
        {
            var city = dc.CityList.Find(CityId);
            dc.CityList.Remove(city);
        }

        public async Task<IEnumerable<City>> GetCitiesAsync()
        {
            return await dc.CityList.ToListAsync();
        }

        public async Task<City> FindCity(int id)
        {
            return await dc.CityList.FindAsync(id);
        }
    }
}
