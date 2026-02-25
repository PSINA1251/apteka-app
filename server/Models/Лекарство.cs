using System.ComponentModel.DataAnnotations;

namespace AptekaAPI.Models
{
    public class Лекарство
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public string Название { get; set; }

        [Required]
        public decimal Цена { get; set; }

        public bool В_наличии { get; set; }
    }
}