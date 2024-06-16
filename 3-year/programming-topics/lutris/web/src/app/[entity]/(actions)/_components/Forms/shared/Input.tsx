"use client";
import { forwardRef, type InputHTMLAttributes } from "react";

interface InputProps {
  label: string;
  name: string;
  placeholder: string;
  type?: InputHTMLAttributes<HTMLInputElement>["type"];
  value?: string;
  onChange: InputHTMLAttributes<HTMLInputElement>["onChange"];
}

const Input = forwardRef<HTMLInputElement, InputProps>(
  ({ label, name, placeholder, type = "text", value, onChange }, ref) => {
    return (
      <div className="flex flex-col text-gray-300 w-full">
        <label htmlFor={name}>{label}</label>
        <input
          ref={ref}
          id={name}
          className="outline-none rounded-md px-3 py-2 mt-2 focus:ring-emerald-600 ring-2 ring-zinc-400 bg-transparent"
          type={type}
          name={name}
          placeholder={placeholder}
          value={value}
          onChange={onChange}
        />
      </div>
    );
  }
);

Input.displayName = "Input";

export default Input;
