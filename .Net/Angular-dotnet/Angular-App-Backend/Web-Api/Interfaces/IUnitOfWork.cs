namespace Web_Api.Interfaces
{
    public interface IUnitOfWork
    {
        ICityRepository CityRepository { get; }
        IUserRepository UserRepository { get; } 

        IPropertyRepository PropertyRepository { get; }

        IPropertyTypeRepository propertyTypeRepository{ get; }
        IFurnishingTypeRepository furnishingTypeRepository { get; }
        Task<bool> SaveAsync();
    }
}
