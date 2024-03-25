package com.noatnoat.base.common.delegate

import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T> observer(
    initialValue: T,
    crossinline onChange: (newValue: T) -> Unit,)
: ReadWriteProperty<Any?, T> = object : ObservableProperty<T>(initialValue) {
        override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) =
            onChange(newValue)
    }

//Hàm `observer` bạn đã cung cấp là một hàm chung trong Kotlin, được sử dụng để tạo một thuộc tính quan sát được.
// Đây là thông tin về đầu vào và đầu ra của nó:
//
//**Đầu vào:**
//1. `initialValue: T`: Giá trị ban đầu cho thuộc tính. `T` ở đây là một kiểu chung, có nghĩa là nó có thể là bất kỳ kiểu dữ liệu nào.
//2. `onChange: (newValue: T) -> Unit`: Một hàm lambda được gọi mỗi khi giá trị của thuộc tính thay đổi. Hàm này nhận giá trị mới của
// thuộc tính làm đối số và không trả về gì (`Unit`).
//
//**Đầu ra:**
//- `ReadWriteProperty<Any?, T>`: Hàm trả về một đối tượng thuộc tính có thể đọc và ghi.
// Đối tượng này sẽ theo dõi sự thay đổi của giá trị và gọi hàm `onChange` mỗi khi giá trị thay đổi.


//---------------------------------------------------------------------------------------------------------------------
//Trong trường hợp này, `onChange` không chỉ là một biến, mà còn là một hàm. Nó được gọi mỗi khi giá trị của thuộc tính thay đổi.
//
//Khi bạn viết `{ newValue -> println("Name has been changed to $newValue") }`, bạn đang tạo một hàm lambda. Hàm này nhận một tham số (`newValue`) và thực hiện một hành động (in ra một thông báo).
//
//Khi bạn gán một giá trị mới cho thuộc tính `name`, hàm `onChange` sẽ được gọi với `newValue` là giá trị mới của thuộc tính. Điều này cho phép bạn thực hiện một hành động cụ thể (trong trường hợp này, in ra một thông báo) mỗi khi giá trị của thuộc tính thay đổi.
//
//Nếu bạn chỉ đặt `onChange` là một biến, bạn sẽ không thể thực hiện được hành động này. Biến `onChange` sẽ chỉ chứa giá trị mới của thuộc tính, nhưng nó sẽ không thể thực hiện hành động in ra
// thông báo mỗi khi giá trị thay đổi. Đó là lý do tại sao `onChange` trong trường hợp này là một hàm, không phải là một biến.







//---------------------------------------------------------------------------------------------------------------------
// Khi bạn sử dụng ReadWriteProperty như một delegate cho một thuộc tính trong Kotlin, các phương thức getValue
// và setValue của nó sẽ được gọi mỗi khi giá trị của thuộc tính được truy cập hoặc thay đổi.
//
//Trong trường hợp của hàm observer, nó tạo ra một đối tượng kế thừa từ ObservableProperty<T>, một lớp trừu tượng mở rộng ReadWriteProperty
// và cung cấp thêm phương thức afterChange. Phương thức afterChange này được gọi mỗi khi giá trị của thuộc tính thay đổi.
//
//Vì vậy, khi bạn gán một giá trị mới cho thuộc tính, phương thức setValue của ReadWriteProperty sẽ được gọi, sau đó là phương thức afterChange của ObservableProperty<T>, và cuối cùng là hàm onChange mà bạn đã cung cấp. Điều này cho phép bạn
// thực hiện một hành động cụ thể mỗi khi giá trị của thuộc tính thay đổi. Trong ví dụ bạn đã cung cấp, hành động đó là in ra một thông báo.



//----------------------------------------------------------------------------------------------------------------------------
//Trong Kotlin, một delegate không nhất thiết phải là một lớp(đa số đều là lớp).
// Một delegate có thể là một đối tượng của bất kỳ lớp nào cung cấp các phương thức `getValue` và `setValue`¹. Trong trường hợp của hàm `observer`,
// nó tạo ra một đối tượng của một lớp ẩn danh kế thừa từ `ObservableProperty<T>`¹.
//
//Lớp ẩn danh này không cần một tên cụ thể và nó cung cấp các phương thức `getValue` và `setValue` cần thiết để hoạt động như một delegate¹.
// Điều này cho phép bạn tạo ra các delegate mà không cần phải định nghĩa một lớp mới cho mỗi delegate¹.
//
//Ngoài ra, việc sử dụng hàm để tạo ra delegate cho phép bạn tùy chỉnh hành vi của delegate mà không cần phải tạo ra một lớp mới cho mỗi biến thể¹.
// Trong trường hợp của hàm `observer`, bạn có thể tùy chỉnh hành động được thực hiện khi giá trị thay đổi bằng cách truyền vào một hàm `onChange`¹.
//
//Vì vậy, mặc dù `observer` không phải là một lớp, nhưng nó vẫn hoạt động như một delegate trong Kotlin¹.



//----------------------------------------------------------------------------------------4
//observer trong trường hợp này hoạt động như một delegate.
// Trong Kotlin, một delegate là một đối tượng cung cấp giá trị cho một thuộc tính và xử lý các thay đổi đối với giá trị đó.
//
//Đúng là bạn có thể tạo một lớp riêng biệt để ghi đè ObservableProperty và sử dụng nó như một delegate.
// Tuy nhiên, Kotlin cũng cho phép bạn tạo một đối tượng ẩn danh ngay trong hàm observer để thực hiện điều này.
// Điều này giúp giảm bớt số lượng mã và làm cho mã dễ đọc hơn.
//
//Khi bạn gọi hàm observer, nó trả về một đối tượng của ObservableProperty với hành vi afterChange đã được tùy chỉnh.
// Đối tượng này sau đó có thể được sử dụng như một delegate.


//---------------------------------------------------------------------------------------------------
//observer là một hàm, không phải là một đối tượng hay lớp. Tuy nhiên, trong thân hàm observer, chúng ta tạo ra một đối tượng ẩn danh (anonymous object) của lớp ObservableProperty.
//
//Đây là cách chúng ta có thể tạo ra một đối tượng với hành vi tùy chỉnh mà không cần định nghĩa một lớp riêng biệt. Đối tượng này ghi đè phương thức afterChange từ ObservableProperty để thực hiện hành động mà chúng ta muốn khi giá trị thay đổi.
//
//Vì vậy, khi bạn gọi hàm observer, nó sẽ trả về một đối tượng của lớp ObservableProperty với hành vi afterChange đã được tùy chỉnh. Đây là lý do tại sao chúng ta nói rằng observer tạo ra một đối tượng, mặc dù observer chính xác là một hàm.