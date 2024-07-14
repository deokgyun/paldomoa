'use client'

import React, { MouseEventHandler } from 'react'

interface ButtonProps extends React.HTMLProps<HTMLButtonElement> {
  containerStyles?: string
  handleClick?: MouseEventHandler<HTMLButtonElement>
  buttonType?: 'button' | 'submit' | 'reset'
  isDisabled?: boolean
  textStyle?: string
  title: string
}

export default function Button({
  containerStyles,
  handleClick,
  buttonType,
  textStyle,
  title,
}: ButtonProps): React.ReactElement {
  return (
    <button
      className={`custom-btn ${containerStyles}`}
      type={buttonType || 'button'}
      disabled={false}
      onClick={handleClick}
    >
      <span className={`flex-1 ${textStyle}`}>{title}</span>
    </button>
  )
}
