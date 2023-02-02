using Web_Api.models;

namespace Web_Api.Data.Repo
{
    public interface ICityRepository
    {
        Task<IEnumerable<City>> GetCitiesAsync();

        void AddCity(City city);

        void DeleteCity(int CityId);

        Task<bool> SaveAsync();

    }
}
