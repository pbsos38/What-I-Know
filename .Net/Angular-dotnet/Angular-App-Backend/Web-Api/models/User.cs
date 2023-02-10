using System.ComponentModel.DataAnnotations;

namespace Web_Api.models
{
    public class User
    {
        public int Id { get; set; }

        [Required]
        public string Username { get; set; }

        [Required]
        public string Password { get; set; }
    }
}
