using AutoMapper;
using Web_Api.Dtos;
using Web_Api.models;

namespace Web_Api.Helpers
{
    public class AutoMapperProfiles : Profile
    {
        public AutoMapperProfiles()
        {
            CreateMap<City, CityDto>();
            CreateMap<CityDto, City>();
            CreateMap<City, CityUpdateDto>();
            CreateMap<CityUpdateDto, City>();
        }
    }
}
