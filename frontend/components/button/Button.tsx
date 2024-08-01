import { MouseEventHandler } from 'react'

interface ButtonProps {
  title: string
  containerStyles?: string
  handleClick: MouseEventHandler<HTMLButtonElement>
  btnType?: 'button' | 'submit' | 'reset'
  textStyles?: string
  rightIcon?: string
  isDisabled?: boolean
}

export default function Button({ title, containerStyles, handleClick, btnType, textStyles, rightIcon }: ButtonProps) {
  return (
    <button className={`custom-btn ${containerStyles}`} type={btnType || 'button'} onClick={handleClick}>
      <span className={`flex-1 ${textStyles}`}>{title}</span>
      {rightIcon && (
        <div className="relative w-6 h-6">
          <span>test</span>
        </div>
      )}
    </button>
  )
}
