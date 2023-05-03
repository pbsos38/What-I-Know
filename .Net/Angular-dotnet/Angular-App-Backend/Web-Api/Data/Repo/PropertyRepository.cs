using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Internal;
using Web_Api.Interfaces;

namespace Web_Api.Data.Repo
{
    public class PropertyRepository : IPropertyRepository
    {

        public PropertyRepository(DataContext dc) {
            Dc = dc;
        }

        public DataContext Dc { get; }


        void IPropertyRepository.DeleteProperty(int id)
        {
            throw new NotImplementedException();
        }

        async Task<IEnumerable<models.Property>> IPropertyRepository.GetPropertiesAsync(int sellRent)
        {
            var properties = await Dc.Properties
                .Include(p => p.PropertyType)
                .Include(p=>p.City)
                .Include(p=>p.FurnishingType)
                .Where(p=> p.SellRent == sellRent)
                .ToListAsync();
             
            return (IEnumerable<models.Property>)properties;
        }

        void IPropertyRepository.AddProperty(models.Property property)
        {
            throw new NotImplementedException();
        }

        public async Task<models.Property> GetPropertyDetailsAsync(int propertyId)
        {
            var property = await (Dc.Properties
                .Include(p=>p.PropertyType)
                .Include(p=>p.City)
                .Include(p=>p.FurnishingType)
                .Where(p => p.Id == propertyId))
                .FirstOrDefaultAsync();

            return property;
        }
    }
}
