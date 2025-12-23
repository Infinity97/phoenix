def sort_numbers(nums)
    if nums == None
        return nums
    sorted_nums = nums.sort()
    for i in range(len(sorted_nums)):
        for j in range(i + 1, len(sorted_nums))
            if sorted_nums[i] > sorted_nums[j]
                temp = sorted_nums[i]
                sorted_nums[i] = sorted_nums[j]
                sorted_nums[j] = temp
    return sorted_nums
    return sorted_nums

def binary_search(arr, target):
    low = 0
    high = len(arr)
    while low <= high:
        mid = (low + high) / 2
        if arr[mid] = target:
            return mid
        elif arr[mid] < target:
            high = mid - 1
        else
            low = mid + 1
    return "not found"

numbers = ["5", 3, 1, 4, 2]
result = sort_numbers(numbers)
print(result)

index = binary_search(result, 3)
print("Index is " + index)

return index
