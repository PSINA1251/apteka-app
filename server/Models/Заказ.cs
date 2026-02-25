using System.ComponentModel.DataAnnotations;

namespace AptekaAPI.Models
{
    public class Заказ
    {
        [Key]
        public int Id { get; set; }

        [Required]
        public string ФИО { get; set; }

        [Required]
        public string Адрес { get; set; }

        [Required]
        public string Телефон { get; set; }

        public DateTime Дата { get; set; } = DateTime.Now;
    }
}