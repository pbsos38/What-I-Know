using Microsoft.EntityFrameworkCore.Metadata.Internal;
using Web_Api.models;

namespace Web_Api.Interfaces
{
    public interface IFurnishingTypeRepository
    {
        Task<IEnumerable<FurnishingType>> GetFurnishingTypeAsync();

    }
}
