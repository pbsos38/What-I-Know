using Microsoft.EntityFrameworkCore.Metadata.Internal;
using Web_Api.models;

namespace Web_Api.Interfaces
{
    public interface IPropertyRepository
    {
        Task<IEnumerable<models.Property>> GetPropertiesAsync(int sellRent);
        void AddProperty(models.Property property);

        void DeleteProperty(int id);
        void AddProperty(Microsoft.EntityFrameworkCore.Metadata.Internal.Property property);
    }
}
