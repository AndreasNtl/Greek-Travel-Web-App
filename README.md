![Screenshot_1](https://user-images.githubusercontent.com/37142918/142828185-093680ec-a3d3-4711-842e-62c6171c6620.png)


Για την υλοποίηση της εφαρμογής έχουν χρησιμοποιηθεί οι τεχνολογίες JSP για επικοινωνία

με την MySQL βάση, JSP Servlets για την εξυπηρέτηση των αιτημάτων της εφαρμογής και

το περιβάλλον ανάπτυξης εφαρμογών του NetBeans.

Έχουν υλοποιηθεί όλα τα ζητούμενα της εκφώνησης με τις εξής λεπτομέρειες:

&nbsp;1) Εμφανίζουμε τα recommendations στην σελίδα καλωσορίσματος, στο κάτω μέρος.

&nbsp;2) Τα recommendations αυτά δημιουργούνται με βάση των ζητουμένων 9) και 10),
δηλαδή χρησιμοποιούν τους αλγορίθμους Nearest Neighbour Collaborative Filtering
καθώς επίσης για την αντιμετώπιση του data sparsity, καταγράφονται στην βάση μας
και χρησιμοποιούνται από τον αλγόριθμο οι αναζητήσεις του κάθε χρήστη
(συγκεκριμένα, το πότε ο κάθε χρήστης δείχνει ενδιαφέρον σε συγκεκριμένα δωμάτια,
μπαίνοντας στην σχετική σελίδα λεπτομερειών του). Επίσης, στα παραδοτέα έχουμε
συμπεριλάβει ένα .ods αρχείο που αποτυπώνει την εικόνα της βάσης σχετικά με τα
views και rates των χρηστών για βοεπαλήθευση του αλγορίθμου.

&nbsp;3) Όλες οι αιτήσεις HTTP είναι κρυπτογραφημένες μέσω του πρωτοκόλλου SSL/TLS.

&nbsp;4) Σε όλες τις σελίδες τις εφαρμογής, περιέχεται στο πάνω μέρος μια μπάρα η οποία
δυναμικά προσαρμόζεται στις ανάγκες του κάθε χρήστη (guest, visitor, owner και
admin). Μέσω της μπάρας αυτής μπορεί να περιηγηθεί ο κάθε χρήστης της
εφαρμογής σε όλες τις επιλογές που ζητάει η εκφώνηση, ανάλογα με τον ρόλο που
διακατέχει στην εφαρμογή.

&nbsp;5) Κατά την διάρκεια ανάπτυξης της εφαρμογής υλοποιήθηκε και εφαρμόστηκε
αποτελεσματικά η διαδικασία της κρυπτογράφησης των κωδικών των χρηστών κατά
την εισαγωγή τους στη βάση μας αλλά και στην διαδικασία ελέγχου κατά την είσοδο.

Παρ’όλα αυτά για λόγους διευκόλυνσης, επιλέξαμε να κρατήσουμε απενεργοποιημένη την συγκεκριμένη μέθοδο καθώς στο δείγμα βάσης που
δημιουργήσαμε χρησιμοποιούμε κωδικούς χωρίς κάποια επεξεργασία.
Η βάση μας περιέχει 71 χρήστες στο σύνολο, 50 ενοικιαστές, 20 οικοδεσπότες, 1 admin.

Usernames/passwords ως εξής:

user: admin pass: admin
user: visitor1 pass: visitor1
user: owner1 pass: owner1
κ.ο.κ.
