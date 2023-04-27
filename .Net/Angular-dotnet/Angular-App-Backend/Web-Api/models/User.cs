using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Web_Api.models
{
    public class User : BaseEntity
    {
        public int Id { get; set; }

        [Required]
        public string Username { get; set; }

        [Required]
        public byte[] Password { get; set; }

        public byte[] PasswordKey { get; set; }

       }
}
