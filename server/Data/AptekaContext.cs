using Microsoft.EntityFrameworkCore;
using AptekaAPI.Models;

namespace AptekaAPI.Data
{
    public class AptekaContext : DbContext
    {
        public AptekaContext(DbContextOptions<AptekaContext> options) : base(options)
        {
        }

        public DbSet<Лекарство> Лекарства { get; set; }
        public DbSet<Заказ> Заказы { get; set; }
        public DbSet<СоставЗаказа> СоставыЗаказов { get; set; }
    }
}