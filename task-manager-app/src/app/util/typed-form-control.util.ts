import { FormControl, AbstractControl } from "@angular/forms";

/**
 * 静的型付けを許容できるFormControlを提供します
 */
export class TypedFormControl<T> extends FormControl{

    _value: T;
    
    public getValue() {
        return this._value;
    }

    public setValue(value) {
        this._value = value;
    }

}