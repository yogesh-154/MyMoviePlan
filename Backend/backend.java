// Main Application Class
@SpringBootApplication
public class MovieTicketBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieTicketBookingApplication.class, args);
    }
}

// Movie Entity Class
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String genre;
    private String language;
    // ... other attributes, constructors, getters, setters
}

// UserRepository Interface
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

// MovieService Class
@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }

    // ... other service methods
}

// MovieController Class
@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    // ... other API endpoints
}
