import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/finances")
public class FinanceController {

    @Autowired
    private FinanceRepository financeRepository;

    @GetMapping
    public Finance getFinance() {
        return financeRepository.findAll().get(0); // Suponiendo que solo hay una entrada en la tabla de finanzas
    }

    @PutMapping
    public Finance updateFinance(@RequestBody Finance updatedFinance) {
        Finance finance = financeRepository.findAll().get(0); // Obtener la única entrada de finanzas
        finance.setBalance(updatedFinance.getBalance());
        return financeRepository.save(finance);
    }

    @PostMapping("/add")
    public Finance addBalance(@RequestParam BigDecimal amount) {
        Finance finance = financeRepository.findAll().get(0); // Obtener la única entrada de finanzas
        finance.setBalance(finance.getBalance().add(amount));
        return financeRepository.save(finance);
    }

    @PostMapping("/subtract")
    public Finance subtractBalance(@RequestParam BigDecimal amount) {
        Finance finance = financeRepository.findAll().get(0); // Obtener la única entrada de finanzas
        finance.setBalance(finance.getBalance().subtract(amount));
        return financeRepository.save(finance);
    }
}