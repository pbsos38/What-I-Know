using Microsoft.EntityFrameworkCore;
using Web_Api.Interfaces;
using Web_Api.models;

namespace Web_Api.Data.Repo
{
    public class PropertyTypeRepository : IPropertyTypeRepository
    {
        private DataContext dc;

        public PropertyTypeRepository(DataContext dc)
        {
            this.dc = dc;
        }

        public async Task<IEnumerable<PropertyType>> GetPropertyTypeAsync()
        {
            return await dc.propertyTypes.ToListAsync();
        }
    }
}
