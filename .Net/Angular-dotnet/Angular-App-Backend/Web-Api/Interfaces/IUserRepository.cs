using Web_Api.models;

namespace Web_Api.Interfaces
{
    public interface IUserRepository
    {
        Task<User> Authenticate(string userName, string password);

        void Register(string username, string password);

        Task<bool> UserAlreadyExists(string username);

        
    }
}
