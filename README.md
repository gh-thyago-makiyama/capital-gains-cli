# 💸 Capital Gains CLI

This project is a command-line application written in **Kotlin** that calculates taxes on stock trading operations (buy/sell) based on Brazilian capital gains tax rules. It processes JSON input line by line from `stdin` and prints the result to `stdout`.

---

## 🧑‍💻 Development

To develop locally:

```bash
./gradlew build
```

Or, to compile and run directly:

```bash
./capital-gains < input.txt
```

The `./capital-gains` script handles automatic build and execution.

---

## 📦 Requirements

| Requirement | Recommended Version  |
| ----------- | -------------------- |
| Java        | 21 (Amazon Corretto) |
| Gradle      | 8.10                 |
| Docker      | 24+ (optional)       |
| Kotlin      | 1.9+                 |

---

## ▶️ Run Without Docker

To run locally with Java and Gradle installed:

```bash
./capital-gains < input.txt
```

This script will:

- Compile the project if needed
- Run the `capitalgainscli-1.0.jar` with stdin as input

---

## 🐳 Run With Docker

To run using Docker:

```bash
./capital-gains --docker < input.txt
```

This will:

- Build the Docker image `capital-gains-app` if not yet available
- Run the application inside a container
- Accept standard input as usual

---

## 🧠 Architecture and Design Patterns

The project uses clean separation of concerns and two classic design patterns:

### 🔹 Command Pattern

Used to encapsulate operations (`buy`, `sell`) as commands. This allows:

- Clear separation of logic per operation
- Easier testing and extension for future operations

**Where it's used:**

- `OperationCommand.kt` (interface)
- `BuyCommand.kt`
- `SellCommand.kt`

### 🔹 Strategy Pattern

Used to encapsulate various tax calculation strategies, avoiding complex conditionals.

**Tax strategies include:**

- `NoTaxStrategy`: when total sale ≤ R\$20,000
- `LossStrategy`: when there's a loss
- `ProfitWithTaxStrategy`: taxable profit with loss deduction

**Where it's used:**

- `TaxStrategy.kt` (interface)
- Implementations under `strategy/`

**Coordinated by:**

- `CapitalGainsProcessor.kt` — maintains state (stock quantity, average cost, accumulated losses)

---

## 🧪 Testing

Each strategy and command is fully unit-testable in isolation. End-to-end tests can be added to validate full CLI behavior.

---

## 📂 Project Structure

```text
src/
├── cli/                    # CLI and terminal IO
├── dtos/                   # DTOs (input/output)
├── processor/
│   ├── CapitalGainsProcessor.kt
│   ├── command/            # Command pattern impls
│   └── strategy/           # Strategy pattern impls
|── utils/                  # Utility functions
inputs/                     # Some example input files
```

---

## ✍️ Example Input (`input.txt`)

```json
[{"operation":"buy", "unit-cost":10.00, "quantity": 10000},{"operation":"sell", "unit-cost":20.00, "quantity": 5000},{"operation":"sell", "unit-cost":5.00, "quantity": 5000}]
```

---

## 📤 Example Output

```json
[{"tax":0},{"tax":10000.00},{"tax":0}]
```

---
