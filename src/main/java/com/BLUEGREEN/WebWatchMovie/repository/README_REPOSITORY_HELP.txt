
** Khi bạn sử dụng JpaRepository trong một repository interface trong dự án Spring Data JPA,
    bạn kế thừa một loạt các phương thức sẵn có để tương tác với cơ sở dữ liệu.
    JpaRepository cung cấp các phương thức CRUD cơ bản cũng như các phương thức truy vấn nâng cao.
    Dưới đây là danh sách các phương thức chính mà JpaRepository cung cấp:

* Các phương thức CRUD cơ bản
* Lưu và cập nhật (Save and Update):

    - S save(S entity) - Lưu một thực thể (entity). Nếu thực thể đã tồn tại, nó sẽ được cập nhật.
    - List<S> saveAll(Iterable<S> entities) - Lưu nhiều thực thể.

* Tìm kiếm (Read):

    - Optional<T> findById(ID id) - Tìm thực thể theo ID.
    - boolean existsById(ID id) - Kiểm tra xem thực thể có tồn tại theo ID không.
    - List<T> findAll() - Tìm tất cả các thực thể.
    - List<T> findAllById(Iterable<ID> ids) - Tìm tất cả các thực thể theo danh sách ID.
    - Page<T> findAll(Pageable pageable) - Tìm tất cả các thực thể với phân trang.
    - List<T> findAll(Sort sort) - Tìm tất cả các thực thể với sắp xếp.

* Xóa (Delete):

    - void deleteById(ID id) - Xóa thực thể theo ID.
    - void delete(T entity) - Xóa một thực thể.
    - void deleteAllById(Iterable<? extends ID> ids) - Xóa tất cả các thực thể theo danh sách ID.
    - void deleteAll(Iterable<? extends T> entities) - Xóa nhiều thực thể.
    - void deleteAll() - Xóa tất cả các thực thể.

* Đếm (Count):

    - long count() - Đếm số lượng thực thể.
    - Các phương thức truy vấn nâng cao

** Ngoài các phương thức CRUD cơ bản, JpaRepository còn cung cấp các phương thức truy vấn nâng cao
    thông qua interface PagingAndSortingRepository:

* Phân trang và sắp xếp:

    - Page<T> findAll(Pageable pageable) - Trả về một trang các thực thể theo phân trang.
    - List<T> findAll(Sort sort) - Trả về danh sách các thực thể theo sắp xếp.

* Phương thức sắp xếp:

    - Iterable<T> findAll(Sort sort) - Trả về các thực thể được sắp xếp theo tham số Sort.
    - Page<T> findAll(Pageable pageable) - Trả về các thực thể với phân trang.

*** Custom Queries
** Ngoài các phương thức mặc định, bạn cũng có thể định nghĩa các phương thức truy vấn tùy chỉnh bằng cách
    sử dụng các cú pháp của Spring Data JPA:

    VD:
    public interface UserRepository extends JpaRepository<User, Long> {
        List<User> findByLastName(String lastName);
        List<User> findByAgeGreaterThan(int age);
    }