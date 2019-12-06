
class IntCode:

    def __init__(self):
        self.input_array = [1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 10, 1, 19, 2, 19, 6, 23, 2, 13, 23, 27, 1, 9,
                       27, 31, 2, 31, 9, 35, 1, 6, 35, 39, 2, 10, 39, 43, 1, 5, 43, 47, 1, 5, 47, 51, 2, 51, 6, 55,
                       2, 10, 55, 59, 1, 59, 9, 63, 2, 13, 63, 67, 1, 10, 67, 71, 1, 71, 5, 75, 1, 75, 6, 79, 1, 10,
                       79, 83, 1, 5, 83, 87, 1, 5, 87, 91, 2, 91, 6, 95, 2, 6, 95, 99, 2, 10, 99, 103, 1, 103, 5,
                       107, 1, 2, 107, 111, 1, 6, 111, 0, 99, 2, 14, 0, 0]
        self.copy_of_input_array = []

    def replace_values(self):
        self.input_array[1] = 12
        self.input_array[2] = 2

    def replace_values_with_specific_inputs(self, noun, verb):
        self.input_array[1] = noun
        self.input_array[2] = verb

    def make_copy_of_input_array(self):
        self.copy_of_input_array = self.input_array[:]

    def reset_input_array(self):
        self.input_array = self.copy_of_input_array[:]

    def change_value_in_position(self, position, value):
        self.input_array[position] = value

    def get_value_at_position(self, position):
        return self.input_array[position]

    def process_input_array(self):
        i = 0
        opcode = 0

        while opcode != 99:
            opcode = self.input_array[i]

            if opcode == 1:
                total = self.get_value_at_position(self.input_array[i+1]) + self.get_value_at_position(self.input_array[i+2])
                self.change_value_in_position(self.input_array[i+3], total)
                i += 4
            elif opcode == 2:
                total = self.get_value_at_position(self.input_array[i+1]) * self.get_value_at_position(self.input_array[i+2])
                self.change_value_in_position(self.input_array[i+3], total)
                i += 4
            elif opcode == 99:
                break
            else:
                i += 4

    def find_inputs_for_correct_output(self):
        i = 0
        j = 0

        for i in range(100):
            for j in range(100):
                self.replace_values_with_specific_inputs(i, j)
                self.process_input_array()
                if(self.input_array[0] == 19690720):
                    return [i, j]
                else:
                    self.reset_input_array()


if __name__ == "__main__":
    intCode = IntCode()

    # part 1.
    intCode.make_copy_of_input_array()
    intCode.replace_values()
    intCode.process_input_array()

    # part 2.
    intCode.reset_input_array()
    [noun,verb] = intCode.find_inputs_for_correct_output()
    print(intCode.input_array)
    print("NOUN: " + str(noun) + " VERB: " + str(verb))