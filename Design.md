## 🧍 Class: Person

### Attributes
- `id: String` ✅  
- `firstName: String` ✅  
- `lastName: String` ✅  
- `email: String` ✅  

### Operations
- `Person(id, firstName, lastName, email)` ✅  
- `getId(): String` ✅  
- `getFirstName(): String` ✅  
- `setFirstName(name: String): void` ✅  
- `getLastName(): String` ✅  
- `setLastName(name: String): void` ✅  
- `getEmail(): String` ✅  
- `setEmail(email: String): void` ✅  
- `getFullName(): String` ✳️  
- `toString(): String` ✳️  
- `equals(obj: Object): boolean` ✳️  
- `hashCode(): int` ✳️  

---

## 📝 Class: TodoItem

### Attributes
- `id: String` ✅  
- `title: String` ✅  
- `description: String` ✳️  
- `deadline: LocalDate` ✅  
- `done: boolean` ✅  
- `creator: Person` ✅  

### Operations
- `TodoItem(id, title, deadline, creator)` ✅  
- `getId(): String` ✅  
- `getTitle(): String` ✅  
- `setTitle(title: String): void` ✅  
- `getDescription(): String` ✳️  
- `setDescription(desc: String): void` ✳️  
- `getDeadline(): LocalDate` ✅  
- `setDeadline(date: LocalDate): void` ✅  
- `isDone(): boolean` ✅  
- `setDone(status: boolean): void` ✅  
- `getCreator(): Person` ✅  
- `isOverdue(): boolean` ✳️  
- `toString(): String` ✳️  
- `equals(obj: Object): boolean` ✳️  
- `hashCode(): int` ✳️  

---

## 🔗 Class: TodoItemTask

### Attributes
- `id: String` ✅  
- `todoItem: TodoItem` ✅  
- `assignee: Person` ✅  
- `assigned: boolean` ✅  
- `done: boolean` ✅  

### Operations
- `TodoItemTask(id, todoItem, assignee)` ✅  
- `getId(): String` ✅  
- `getTodoItem(): TodoItem` ✅  
- `setTodoItem(item: TodoItem): void` ✅  
- `getAssignee(): Person` ✅  
- `setAssignee(person: Person): void` ✅  
- `isAssigned(): boolean` ✳️  
- `setAssigned(status: boolean): void` ✅  
- `isDone(): boolean` ✅  
- `setDone(status: boolean): void` ✅  
- `markDone(): void` ✳️  
- `markUndone(): void` ✳️  
- `toString(): String` ✳️  
- `equals(obj: Object): boolean` ✳️  
- `hashCode(): int` ✳️