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
            
            CreateMap<UserDto, User>();
            CreateMap<User, UserDto>();

            CreateMap<Property, PropertyList>()
                .ForMember(d => d.City, opt => opt.MapFrom(src => src.City.Name))
                .ForMember(d => d.FurnishingType, opt => opt.MapFrom(src => src.FurnishingType.Name))
                .ForMember(d => d.PropertyType, opt => opt.MapFrom(src => src.PropertyType.Name))
                .ForMember(d => d.Country, opt => opt.MapFrom(src => src.City.Country));
        }
    }
}
